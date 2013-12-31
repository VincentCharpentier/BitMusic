/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bitmusic.hmi.modules.tab;

import bitmusic.hmi.patterns.AbstractModel;
import bitmusic.music.data.Song;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author IHM
 */
public final class TabModel extends AbstractModel {

    private String requestOrigin;   // Origine de la requête : MyProfile ou SearchBar ou OnlineUsers
    private String requestText;     // Texte entré dans la SearchBar
    private String requestFilter;   // Filtre sélectionné dans la SearchBar

    private TabTableModel modeleTable = new TabTableModel();

    /**
     * Constructor of TabModel
     */
    public TabModel() {
        super();
    }

    /**
     *
     */
    public class TabTableModel extends AbstractTableModel {
        private String[] columnNames = { "Titre", "Artiste", "Album", "Éditer","Supprimer", "Infos", "Noter", "Sauver" };
        private ArrayList<Song> arrayListSong = new ArrayList();

        /**
         *
         */
        public TabTableModel() {
            super();
        }

        @Override
        public Object getValueAt(int row, int col) {
            switch (col) {
                case 0:
                    return this.arrayListSong.get(row).getTitle();
                case 1:
                    return this.arrayListSong.get(row).getArtist();
                case 2:
                    return this.arrayListSong.get(row).getAlbum();
                case 3:
                    return "E";
                case 4:
                    return "SU";
                case 5:
                    return "I";
                case 6:
                    return "N";
                case 7:
                    return "S";
                default:
                    return null;
            }
        }

        /**
         * Returns song according to its row
         * @param row
         * @return Song
         */
        public Song getSongAt(int row) {
            return this.arrayListSong.get(row);
        }

        /**
         * Add song to a list
         * @param song
         */
        public void addSong(final Song song) {
            this.arrayListSong.add(song);
            fireTableRowsInserted(this.arrayListSong.size()-1, this.arrayListSong.size()-1);
            (TabModel.this).notifyObservers("SONG_ADDED");
        }

        /**
         * Returns a list of songs
         * @param listSongs
         */
        public void addSongs(final ArrayList<Song> listSongs) {
            for (int i = 0; i < listSongs.size(); i++) {
                this.addSong(listSongs.get(i));
            }
        }

        /**
         * Deletes a song according to its Id
         * @param songId
         */
        public void removeSong(final Song songId) {
            for (int row = 0; row < this.arrayListSong.size(); row++) {
                if (this.arrayListSong.get(row).getSongId().equals(songId)) {
                    this.arrayListSong.remove(row);
                    fireTableRowsDeleted(row, row);
                }
            }
            (TabModel.this).notifyObservers("SONG_REMOVED");
        }

        /**
         * Deletes all the songs
         */
        public void removeAllSongs() {
            for (int row = 0; row < this.arrayListSong.size(); row++) {
                fireTableRowsDeleted(row, row);
            }
            this.arrayListSong.clear();
            (TabModel.this).notifyObservers("ALL_SONGS_REMOVED");
        }

        /**
         * Checks if a cell is editable
         * @param row
         * @param col
         * @return boolean
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            if (col == 3 || col == 4 || col == 5 || col == 6 || col == 7) {
                return true;
            }
            return false;
        }

        /**
         * Returns number of columns
         * @return
         */
        @Override
        public int getColumnCount() {
            return this.columnNames.length;
        }

        /**
         * Returns the number of rows
         * @return
         */
        @Override
        public int getRowCount() {
            return this.arrayListSong.size();
        }

        /**
         * Returns a name of a column
         * @param col
         * @return String
         */
        @Override
        public String getColumnName(int col) {
            return this.columnNames[col];
        }

        /**
         * Returns names of columns
         * @return String[]
         */
        public String[] getColumnNames() {
            return this.columnNames;
        }

        /**
         *
         * @param stringTable
         */
        public void setColumnNames(String[] stringTable) {
            this.columnNames = stringTable;
        }

        /**
         * Returns a list of songs
         * @return
         */
        public ArrayList<Song> getSongs() {
            return this.arrayListSong;
        }

        /**
         * Updates a list of songs
         * @param songList
         */
        public void setSong(ArrayList<Song> songList) {
            this.arrayListSong = songList;
            (TabModel.this).notifyObservers("LIST_SONGS_SET");
        }
    }

    /**
     *
     * @return modeleTable
     */
    public TabTableModel getModeleTable() {
        return this.modeleTable;
    }

    /**
     *
     * @return requestOrigin
     */
    public String getRequestOrigin() {
        return this.requestOrigin;
    }

    /**
     *
     * @param requestOrigin
     */
    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    /**
     *
     * @return requestText
     */
    public String getRequestText() {
        return this.requestText;
    }

    /**
     *
     * @param requestText
     */
    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    /**
     *
     * @return requestFilter
     */
    public String getRequestFilter() {
        return this.requestFilter;
    }

    /**
     *
     * @param requestFilter
     */
    public void setRequestFilter(String requestFilter) {
        this.requestFilter = requestFilter;
    }
}
