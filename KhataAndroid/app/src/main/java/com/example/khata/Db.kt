package com.example.khata

import android.content.Context
import androidx.room.*

/** Poori shop ka data (JSON) ek row mein — isi par app chalti hai. */
@Entity(tableName = "snapshot")
data class Snapshot(@PrimaryKey val id: Int = 1, val json: String, val updatedAt: Long)

/** Roz ka automatic backup (aakhri 14 din ke). */
@Entity(tableName = "backup")
data class Backup(@PrimaryKey(autoGenerate = true) val id: Long = 0, val json: String, val createdAt: Long)

@Dao
interface KhataDao {
    @Query("SELECT * FROM snapshot WHERE id = 1") fun get(): Snapshot?
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun put(s: Snapshot)
    @Insert fun addBackup(b: Backup)
    @Query("SELECT * FROM backup ORDER BY id DESC LIMIT 1") fun lastBackup(): Backup?
    @Query("DELETE FROM backup WHERE id NOT IN (SELECT id FROM backup ORDER BY id DESC LIMIT 14)") fun trim()
}

@Database(entities = [Snapshot::class, Backup::class], version = 1, exportSchema = false)
abstract class KhataDb : RoomDatabase() {
    abstract fun dao(): KhataDao
    companion object {
        @Volatile private var inst: KhataDb? = null
        fun get(c: Context): KhataDb = inst ?: synchronized(this) {
            inst ?: Room.databaseBuilder(c.applicationContext, KhataDb::class.java, "khata.db").build().also { inst = it }
        }
    }
}
