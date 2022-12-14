package no.h598062.aoc2022.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NoSpaceLeftOnDevice {

	public static void main(String[] args) {
		String       filepath   = "./resources/input-day7.txt";
		List<Folder> folderList = new ArrayList<>();
		List<String> terminal   = new ArrayList<>();
		// try-with-resources
		try (BufferedReader file = new BufferedReader(new FileReader(filepath))) {
			String currentLine;
			while ((currentLine = file.readLine()) != null) {
				terminal.add(currentLine);
			}

		} catch (IOException e) {
			System.out.println("Could not open file: " + filepath);
		}
		Folder cwd  = new Folder("/");
		String cwp  = cwd.getPath();
		Folder root = cwd;
		folderList.add(cwd);
		System.out.println("Current path: " + cwp);
		for (int i = 1, terminalSize = terminal.size(); i < terminalSize; i++) {
			String line = terminal.get(i);
			if (line.charAt(0) == '$') {
				String[] strings = line.split(" ");
				if (strings[1].equals("cd")) {
					if (strings[2].equals("..")) {
						cwd = cwd.getParent();
					} else {
						cwd = cwd.getChildFolder(strings[2] + "/");
					}
					cwp = cwd.getPath();
					System.out.println("Current path: " + cwp);
				}
			} else {
				Folder newFolder = cwd.add(line);
				if (newFolder != null) {
					folderList.add(newFolder);
				}
			}
		}
		root.updateSize();

		int fssize           = 70000000;
		int updateSize       = 30000000;
		int currentUsed      = root.getSize();
		int currentAvailable = fssize - currentUsed;
		if (currentAvailable < updateSize) {
			int                amountToFree = updateSize - currentAvailable;
			// ArrayList<Folder>  bigger       = new ArrayList<>();
			ArrayList<Integer> biggersizes  = new ArrayList<>();
			for (Folder f : folderList) {
				int s = f.getSize();
				if (s >= amountToFree) {
					// bigger.add(f);
					biggersizes.add(s);
				}
			}
			biggersizes.sort(null);
			System.out.println("total size of directory to delete is " + biggersizes.get(0));
		}
		root.printFolderTree(0);
	}
}

class Folder {
	private final String            path; // total path including its own name and trailing "/"
	private final String            folderName; // its own name + trailing "/"
	private final ArrayList<Folder> childFolders;
	private final ArrayList<Filex>  files;

	private final Folder parent;

	private int size; // calculated size of all nested child elements
	private int nestedElements; // total of all child files and folders

	public Folder(String path, String folderName, Folder parent) {
		this.path           = path;
		this.folderName     = folderName;
		this.parent         = parent;
		this.size           = 0;
		this.childFolders   = new ArrayList<>();
		this.files          = new ArrayList<>();
		this.nestedElements = 0;
	}

	public Folder(String folderName) {
		this.path           = folderName;
		this.folderName     = folderName;
		this.parent         = this;
		this.size           = 0;
		this.childFolders   = new ArrayList<>();
		this.files          = new ArrayList<>();
		this.nestedElements = 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Folder folder = (Folder) o;
		return path.equals(folder.path) && folderName.equals(folder.folderName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(path, folderName);
	}

	public Folder getParent() {
		return parent;
	}

	public String getPath() {
		return path;
	}

	public int getSize() {
		return size;
	}

	private int getNestedElements() {
		return this.nestedElements;
	}

	public Folder getChildFolder(String name) {
		int i = this.childFolders.indexOf(new Folder(this.path + name, name, parent));
		if (i != -1) {
			return this.childFolders.get(i);
		} else {
			return null;
		}
	}

	public void addFile(Filex file) {
		this.files.add(file);
		this.size += file.size();
		this.nestedElements += 1;
	}

	public void addDir(Folder folder) {
		this.childFolders.add(folder);
		this.size += folder.getSize();
		this.nestedElements += 1 + folder.getNestedElements();
	}

	public Folder add(String s) {
		String[] strings = s.split(" ");
		if (strings.length > 2) {
			throw new RuntimeException("Input string " + s + " has more than one whitespace");
		}
		if (strings[0].equals("dir")) {
			Folder newFolder = new Folder(this.getPath() + strings[1] + "/", strings[1] + "/", this);
			this.addDir(newFolder);
			return newFolder;
		} else {
			this.addFile(new Filex(strings[1], Integer.parseInt(strings[0])));
			return null;
		}
	}

	public void updateSize() {
		int s  = 0;
		int ne = 0;
		for (Filex file : this.files) {
			s += file.size();
			ne += 1;
		}
		for (Folder folder : this.childFolders) {
			folder.updateSize();
			s += folder.getSize();
			ne += folder.getNestedElements();
		}
		this.size           = s;
		this.nestedElements = ne;
	}

	public void printFolderTree(int depth) {
		if (depth == 0) {
			System.out.println(this.folderName + "  -  " + this.size);
		} else {
			System.out.println(("  ".repeat(depth-1)) + "  |- " + this.folderName + "  -  " + this.size);
		}
		if (!this.files.isEmpty()) {
			for (Filex f : this.files) {
				System.out.println(("  ".repeat(depth)) + "  -- " + f.name() + "  -  " + f.size());
			}
		}
		if (!this.childFolders.isEmpty()) {
			for (Folder f : this.childFolders) {
				f.printFolderTree(depth +1);
			}
		}
	}
}

record Filex(String name, int size) {}
