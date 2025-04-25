public class Photo {

    private String path;
    Linkedlist<String> allTags = new Linkedlist<>();

    // Constructor
    public Photo(String path, Linkedlist<String> tags) {
        this.path = path;

        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                allTags.insert(tags.retrieve());
                tags.findNext();
            }
            allTags.insert(tags.retrieve());
        }
    }

    // Return all tags associated with the photo
    public Linkedlist<String> getTags() {
        Linkedlist<String> tagsNew = new Linkedlist<String>();

        if (!allTags.empty()) {
            allTags.findFirst();
            while (!allTags.last()) {
                tagsNew.insert(allTags.retrieve());
                allTags.findNext();
            }
            tagsNew.insert(allTags.retrieve());
        }
        return tagsNew;
    }

    @Override
    public String toString() {
        String str = "Photo{" + "path=" + path + ", allTags=";

        allTags.findFirst();
        while (!allTags.last()) {
            str += allTags.retrieve().toString() + "; ";
            allTags.findNext();
        }

        str += allTags.retrieve().toString() + "}";
        return str;
    }

    // Return the path (full file name) of the photo. A photo is uniquely identified by its path.
    public String getPath() {
        return path;
    }
}





