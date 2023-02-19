package org.asgrim.cfttestapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CardDetails::class], version = 1)
abstract class CardDetailsDatabase: RoomDatabase() {
    abstract val cardDetailsDAO: CardDetailsDAO

    companion object{
        @Volatile
        private var INSTANCE: CardDetailsDatabase? = null
        fun getInstance(context: Context): CardDetailsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardDetailsDatabase::class.java,
                        "bebra_db"
                    ).build()
                }
                return instance
            }
        }
    }
}