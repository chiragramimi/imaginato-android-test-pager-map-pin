package com.imaginato.randomusers.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.randomusers.data.randomuser.entity.RandomUserItem
import com.imaginato.randomusers.domain.base.Result
import com.imaginato.randomusers.domain.randomuser.usecase.FetchRandomUserUseCase
import com.imaginato.randomusers.ui.base.BaseViewModel
import com.imaginato.randomusers.ui.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Map ViewModel for getting and storing random user data
 * RandomUser's live data
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val fetchRandomUserUseCase: FetchRandomUserUseCase
) : BaseViewModel() {

    private val _randomUserLiveEvent = MutableLiveData<ArrayList<RandomUserItem>>(arrayListOf())
    val mRandomUserLiveEvent: LiveData<ArrayList<RandomUserItem>> = _randomUserLiveEvent

    private val _progressEvent = SingleLiveEvent<Boolean>()
    val mProgressEvent: SingleLiveEvent<Boolean> = _progressEvent

    private val _errorEvent = SingleLiveEvent<String>()
    val mErrorEvent: SingleLiveEvent<String> = _errorEvent


    /**
     * calling random user use case method for calling API
     */
    fun getRandomUsers() {
        if (_progressEvent.value == true) {
            return
        }
        _progressEvent.value = true
        fetchRandomUserUseCase.invoke(
            scope = viewModelScope,
            params = FetchRandomUserUseCase.Param(5)
        ) { result ->
            _progressEvent.value = false
            if (result is Result.Success) {
                _randomUserLiveEvent.value?.apply {
                    addAll(result.value.results)
                    _randomUserLiveEvent.postValue(this)
                }
            } else if (result is Result.Error) {
                _errorEvent.value = result.error.message
            }
        }
    }

    /**
     * To clear the random user list from livedata
     */
    fun clearRandomUsers() {
        _randomUserLiveEvent.value?.clear()
    }
}
