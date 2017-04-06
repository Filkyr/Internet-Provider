package by.bsuir.dao;

/**
 * Interface that contains methods to start working with the data source
 */
public interface SourceInit {
    /**
     * Initialize all the necessary resources to work with the data source
     */
    void init();

    /**
     * Destroy all held resources, which used for the data source working
     */
    void destroy();
}
