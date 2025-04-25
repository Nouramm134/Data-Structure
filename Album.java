public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int NbComps;

    // Constructor
    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        NbComps = 0;
    }

    // Return the name of the album
    public String getName() {
        return name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return condition;
    }

    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        return NbComps;
    }

    // Return the manager
    public PhotoManager getManager() {
        return manager;
    }

    // Return all photos that satisfy the album condition
    public Linkedlist<Photo> getPhotos() {
        Linkedlist<Photo> filteredPhotos = new Linkedlist<>();
        Linkedlist<Photo> allManagedPhotos = manager.getPhotos();

        if (!allManagedPhotos.empty()) {
            allManagedPhotos.findFirst();
            while (!allManagedPhotos.last()) {
                Photo p = allManagedPhotos.retrieve();
                filteredPhotos.insert(new Photo(p.getPath(), p.getTags()));
                allManagedPhotos.findNext();
            }
            Photo p = allManagedPhotos.retrieve();
            filteredPhotos.insert(new Photo(p.getPath(), p.getTags()));
        }

        NbComps = 0;

        if (!condition.equals("")) {
            String[] keywords = condition.split(" AND ");

            filteredPhotos.findFirst();
            while (!filteredPhotos.last()) {
                Photo photo = filteredPhotos.retrieve();
                if (!allAvilable(photo.allTags, keywords))
                    filteredPhotos.remove();
                else
                    filteredPhotos.findNext();
            }

            Photo last = filteredPhotos.retrieve();
            if (!allAvilable(last.allTags, keywords))
                filteredPhotos.remove();
            else
                filteredPhotos.findNext();
        }

        return filteredPhotos;
    }

    private boolean allAvilable(Linkedlist<String> tags, String[] keywords) {
        boolean valid = true;

        if (tags.empty())
            valid = false;
        else {
            for (int i = 0; i < keywords.length && valid; i++) {
                boolean found = false;
                tags.findFirst();

                while (!tags.last()) {
                    NbComps++;
                    if (tags.retrieve().compareToIgnoreCase(keywords[i]) == 0) {
                        found = true;
                        break;
                    }
                    tags.findNext();
                }

                if (!found) {
                    NbComps++;
                    if (tags.retrieve().compareToIgnoreCase(keywords[i]) == 0)
                        found = true;
                }

                if (!found)
                    valid = false;
            }
        }

        return valid;
    }
}

