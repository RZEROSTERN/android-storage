package com.wizeline.androidstorage.fragments

import androidx.lifecycle.*
import com.wizeline.androidstorage.MainApplication
import com.wizeline.androidstorage.data.model.PersonalData
import com.wizeline.androidstorage.repository.PersonalDataRepository
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    lateinit var personalDataRepository: PersonalDataRepository

    private val insertResultMutable: MutableLiveData<Long> = MutableLiveData()
    val insertResult: LiveData<Long> get() = insertResultMutable

    private val deleteResultMutable: MutableLiveData<Int> = MutableLiveData()
    val deleteResult: LiveData<Int> get() = deleteResultMutable

    private val personalDataMutable: MutableLiveData<PersonalData> = MutableLiveData()
    val personalData: LiveData<PersonalData> get() = personalDataMutable

    fun initRoom(application: MainApplication) {
        personalDataRepository = PersonalDataRepository(application)
    }

    fun getData(email: String) {
        viewModelScope.launch {
            personalDataMutable.postValue(personalDataRepository.getData(email))
        }
    }

    fun insertData(personalData: PersonalData) {
        viewModelScope.launch {
            insertResultMutable.postValue(personalDataRepository.insertData(personalData))
        }
    }

    fun deleteData(email: String) {
        viewModelScope.launch {
            deleteResultMutable.postValue(personalDataRepository.deleteData(email))
        }
    }
}