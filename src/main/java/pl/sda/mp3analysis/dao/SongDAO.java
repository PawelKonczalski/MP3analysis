package pl.sda.mp3analysis.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.sda.mp3analysis.config.HibernateUtils;
import pl.sda.mp3analysis.entity.Song;

import javax.persistence.EntityManager;
import java.util.List;

public class SongDAO implements DaoInterface<Song>{

    private Session currentSession;
    private Transaction currentTransaction;

    private EntityManager entityManager;


    public SongDAO() {
    }

    public void openSession(){
        currentSession = HibernateUtils.getSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession(){
        currentTransaction.commit();
        currentSession.close();
    }

    @Override
    public Song save(Song entity) {
        currentSession.save(entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        List<Song> songs = currentSession.createQuery("SELECT b FROM Song b", Song.class).getResultList();
        return songs;
    }

    @Override
    public List<Song> findByTitle(String title) {
        Criteria criteria = currentSession.createCriteria(Song.class);
        criteria.add(Restrictions.eq("title", title));
        List<Song> songs = criteria.list();
        return songs;
    }

    @Override
    public List<Song> findByArtist(String artist) {
        Criteria criteria = currentSession.createCriteria(Song.class);
        criteria.add(Restrictions.eq("artist", artist));
        List<Song> songs = criteria.list();
        return songs;
    }


}
