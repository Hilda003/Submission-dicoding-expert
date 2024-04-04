package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.local.room.DBMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DBMovie {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("hilda".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            DBMovie::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()

    }

    @Provides
    fun provideDao(db: DBMovie) = db.dao()
}