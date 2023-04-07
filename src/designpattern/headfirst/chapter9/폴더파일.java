package designpattern.headfirst.chapter9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class 폴더파일 {
	static int cnt = 0;
	
	//component 
	abstract static class FolderOrFile{
		private String name;
		private boolean isFolder;
		
		protected FolderOrFile(String name, boolean isFolder) {
			this.name = name;
			this.isFolder = isFolder;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isFolder() {
			return isFolder;
		}
		public void setFolder(boolean isFolder) {
			this.isFolder = isFolder;
		}
		public void add(FolderOrFile folderOrFile) {
			throw new UnsupportedOperationException();
		}
		public void remove(FolderOrFile folderOrFile) {
			throw new UnsupportedOperationException();
		}
		public void print() {
			throw new UnsupportedOperationException();
		}
		
	}
	//composite
	static class MyFolder extends FolderOrFile{
		List<FolderOrFile> folderOrFiles = new ArrayList<>();
		
		@Override
		public void add(FolderOrFile folderOrFile) {
			this.folderOrFiles.add(folderOrFile);
		}
		
		public MyFolder(String name, boolean isFolder) {
			super(name, isFolder);
		}
		@Override
		public void print() {
			for(FolderOrFile fof : folderOrFiles) {
				System.out.println(cnt+++" "+fof);
			}
		}
		@Override
		public String toString() {
			return "[Folder]"+getName();
		}
		
	}
	//leaf
	static class MyFile extends FolderOrFile{
		private String description;
		
		public MyFile(String name, boolean isFolder, String description) {
			super(name, isFolder);
			this.description = description;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@Override
		public void print() {
			System.out.println(cnt+++" "+this);
		}
		@Override
		public String toString() {
			return "[File]"+getName()+" "+getDescription();
		}
	}
	
	public static void main(String[] args) {
		File file = new File("c:/git");
		
		FolderOrFile fof = new MyFolder(file.getName(), true);
		
		copy(file, fof);
		
		fof.print();
		
	}
	
	static void copy(File file, FolderOrFile folderOrFile) {
		if(file.isDirectory()) {
			folderOrFile.add(new MyFolder(file.getName(), true));
			
			for(File f : file.listFiles()) {
				copy(f , folderOrFile);
			}
		}else {
			folderOrFile.add(new MyFile(file.getName(), false, file.length()+"바이트" ));
		}
	}
}
