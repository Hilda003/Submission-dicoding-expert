package com.example.submission_dicoding_expert.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.UseCaseMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCaseMovie: UseCaseMovie) : ViewModel() {

    private val _queryChannel = MutableStateFlow("")
    private val queryChannel: StateFlow<String> = _queryChannel

    fun setSearchQuery(search: String) {
        _queryChannel.value = search
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val movieResult = queryChannel
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            useCaseMovie.movieSearch(it)
        }.asLiveData()


    fun getMovies() = useCaseMovie.allMovie().asLiveData()
}