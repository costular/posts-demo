package com.costular.postsdemo.data.database

import androidx.room.*
import io.reactivex.Completable

@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: List<T>): List<Long>

    @Update
    abstract fun update(obj: T): Completable

    @Update
    abstract fun update(obj: List<T>): Completable

    @Transaction
    open fun insertOrUpdate(obj: T): Completable {
        return Completable.fromCallable {
            val id = insert(obj)
            if (id == EXISTS) {
                update(obj)
            }
        }
    }

    @Transaction
    open fun insertOrUpdate(objList: List<T>): Completable {
        return Completable.fromCallable {
            val insertResult = insert(objList)
            val updateList = mutableListOf<T>()

            insertResult.indices.forEach { i ->
                if (insertResult[i] == -1L) updateList.add(objList[i])
            }
            if (updateList.isNotEmpty()) {
                update(updateList)
            }
        }
    }

    companion object {
        private const val EXISTS = -1L
    }
}