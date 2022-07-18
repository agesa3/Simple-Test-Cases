package com.agesadev.simpetestcases.ui

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.agesadev.simpetestcases.api.ApiService
import com.agesadev.simpetestcases.local.AppDatabase
import com.agesadev.simpetestcases.model.UserBody
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivityRepoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var apiService: ApiService

    lateinit var mainActivityRepo: MainActivityRepo
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    @Before
    fun setUp() {
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext()
            ,AppDatabase::class.java).build()

        mainActivityRepo = MainActivityRepo(apiService = apiService, localDb = appDatabase)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        appDatabase.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val userEntity = UserBody("Random name", "test@mail.com")
        mainActivityRepo.insertUserToDb(userEntity)
        val users = mainActivityRepo.getAllUsersFromDb()
        assertThat(users).contains(userEntity)
    }


    @Test
    fun deleteUser() {
        runBlocking {
            val userEntity = UserBody("agesa", "agesa@gmail.com")
            mainActivityRepo.insertUserToDb(userEntity)
            mainActivityRepo.deleteUserFromDb(userEntity)
            val users = mainActivityRepo.getAllUsersFromDb()
            assertThat(users).doesNotContain(userEntity)
        }
    }

    @Test
    fun updateUserLocal() {
        runBlocking {
            val user = UserBody("agesa", "agesa@gmail.com")
            mainActivityRepo.insertUserToDb(user)
            val newUpdatedUser = user.copy(name = "Agesa Updated")
            mainActivityRepo.updateUserLocal(newUpdatedUser)
            val allUsers = mainActivityRepo.getAllUsersFromDb()
            assertThat(allUsers).contains(newUpdatedUser)
        }
    }


//    @Test
//    fun responseStatusShouldBeSuccess() = runBlocking {
//        mockWebServer.enqueue(
//            MockResponse()
//                .setBody(
//                    MockResponseFileReader(
//                        "api_responses/success.json"
//                    ).content
//                ).setResponseCode(200)
//        )
//
//        val userBody = UserBody("random name", "random@mail.com")
//        val serverMockRes = mainActivityRepo.createNewUser(userBody)
//
//        assertTrue(serverMockRes.isSuccessful && serverMockRes.body()?.status == "success")
//
//    }

    @After
    fun shut() {
        mockWebServer.shutdown()
    }
}