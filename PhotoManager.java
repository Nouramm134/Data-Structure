public class PhotoManager {

    Linkedlist<Photo> photoList;

    // Constructor
    public PhotoManager() {
        photoList = new Linkedlist<Photo>();
    }

    // Return all managed photos
    public Linkedlist<Photo> getPhotos() {
        return photoList;
    }

    // Add a photo
    public void addPhoto(Photo p) {
        if (!isPhotoExists(p.getPath(), photoList)) {
            photoList.insert(p);
        }
    }

    // Delete a photo
    public void deletePhoto(String targetPath) {
        if (!isPhotoExists(targetPath, photoList))
            return;

        if (!photoList.empty()) {
            photoList.findFirst();
            while (!photoList.last()) {
                Photo current = photoList.retrieve();
                if (current.getPath().equalsIgnoreCase(targetPath)) {
                    photoList.remove();
                    return;
                }
                photoList.findNext();
            }

            Photo last = photoList.retrieve();
            if (last.getPath().equalsIgnoreCase(targetPath)) {
                photoList.remove();
            }
        }
    }

    // Check if a photo is already in the list
    private boolean isPhotoExists(String pathToCheck, Linkedlist<Photo> listRef) {
        if (listRef.empty())
            return false;

        listRef.findFirst();
        while (!listRef.last()) {
            if (listRef.retrieve().getPath().equalsIgnoreCase(pathToCheck))
                return true;
            listRef.findNext();
        }

        return listRef.retrieve().getPath().equalsIgnoreCase(pathToCheck);
    }
}
