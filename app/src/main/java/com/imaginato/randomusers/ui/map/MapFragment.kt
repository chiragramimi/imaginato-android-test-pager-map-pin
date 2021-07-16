package com.imaginato.randomusers.ui.map

import android.content.res.Resources
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.imaginato.randomusers.R
import com.imaginato.randomusers.common.extension.*
import com.imaginato.randomusers.data.randomuser.entity.RandomUserItem
import com.imaginato.randomusers.databinding.FragmentMapBinding
import com.imaginato.randomusers.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

/**
 * MapFragment used to display list of users with their location marker on map.
 * On Click of user item marker will display in center with user's name
 * On click of marker user will see user details fragment screen
 */
@AndroidEntryPoint
class MapFragment : BaseViewModelFragment<MapViewModel, FragmentMapBinding>(R.layout.fragment_map),
    GoogleMap.OnMarkerClickListener {

    private var mGoogleMap: GoogleMap? = null

    /**
     * Callback method for on map ready
     * Here we have applied map style to customize the view of map
     * Also init the UI of users
     */
    private val mCallback = OnMapReadyCallback { googleMap ->
        mGoogleMap = googleMap
        mGoogleMap?.setOnMarkerClickListener(this)
        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this.requireContext(), R.raw.style_json
                )
            )
            if (!success) {
                Timber.e("Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Timber.e("Error: ${e.printStackTrace()}")
        }
        initUsersView()
    }

    /**
     * Random user's adapter an it's item click callback handled
     */
    private val mRandomUserAdapter by lazy {
        RandomUserAdapter { user ->
            displayMarkerWithInfoWindow(user)
        }
    }

    /**
     * @param user Random User Model object
     * Displaying map info window on map after finish of moving animation
     */
    private fun displayMarkerWithInfoWindow(user: RandomUserItem) {
        user.location?.coordinates?.apply {
            latitude?.let { lat ->
                longitude?.let { long ->
                    val latLong = LatLng(lat, long)
                    mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLong),
                        object : GoogleMap.CancelableCallback {
                            override fun onFinish() {
                                user.marker?.showInfoWindow()
                            }

                            override fun onCancel() {
                            }
                        })
                }
            }
        }
    }

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun isMultipleLoad() = false

    override fun buildViewModel() = initActivityViewModel<MapViewModel>()

    /**
     * To init the views and listener
     */
    override fun initViews() {
        super.initViews()
        initMap()
        handleListener()
    }

    /**
     * Initialize the map
     */
    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(mCallback)
    }

    /**
     * ClickListener of Refresh floating button
     */
    private fun handleListener() {
        mBinding.fabRefresh.setOnClickListener {
            mViewModel.clearRandomUsers()
            mGoogleMap?.clear()
            mRandomUserAdapter.clearAdapter()
            initUsersView()
        }
    }

    /**
     * setup the adapter and call the getRandomUser API
     */
    private fun initUsersView() {
        if (mViewModel.mRandomUserLiveEvent.value.isNullOrEmpty()) {
            mViewModel.getRandomUsers()
        }
        mBinding.rvRandomUser.adapter = mRandomUserAdapter
        mBinding.rvRandomUser.loadMore(::isLoadMoreRequired) {
            mViewModel.getRandomUsers()
        }
    }

    private fun isLoadMoreRequired(): Boolean {
        return mViewModel.mProgressEvent.value != true
    }

    /**
     * Live data observer
     */
    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(mViewModel) {
            mRandomUserLiveEvent.observe(viewLifecycleOwner) {
                mBinding.pbDashboard.hideView()
                mRandomUserAdapter.hideLoadMore()
                mRandomUserAdapter.setData(it)
                showMarkerOnMap(it)
            }
            mProgressEvent.safeObserve(viewLifecycleOwner) {
                if (it) {
                    if (mRandomUserAdapter.itemCount == 0) {
                        mBinding.pbDashboard.showView()
                    } else {
                        mRandomUserAdapter.showLoadMore(
                            RandomUserItem()
                        )
                    }
                }
            }
            mErrorEvent.safeObserve(viewLifecycleOwner) {
                mBinding.pbDashboard.hideView()
                mRandomUserAdapter.hideLoadMore()
                Timber.e(it)
            }
        }
    }

    /**
     * @param userList list of users
     * Adding marker on map and also storing marker object in user list
     */
    private fun showMarkerOnMap(userList: ArrayList<RandomUserItem>?) {
        userList?.forEachIndexed { index, user ->
            user.location?.coordinates?.apply {
                mGoogleMap?.also { map ->
                    val marker = latitude?.let { it1 -> longitude?.let { it2 -> LatLng(it1, it2) } }
                    marker?.also {
                        val markerPlace = map.addMarker(
                            MarkerOptions()
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                                .position(it)
                                .title(user.name?.let { name ->
                                    "${name.title} ${name.first} ${name.last}"
                                } ?: "")
                        )
                        user.marker = markerPlace
                        markerPlace?.tag = index
                    }
                }
            }
        }
    }

    /**
     * @param marker Marker object
     * callback method of GoogleMap.OnMarkerClickListener
     */
    override fun onMarkerClick(marker: Marker): Boolean {
        val position = marker.tag as Int
        mViewModel.mRandomUserLiveEvent.value?.get(position)?.let {
            findNavController().navigate(
                MapFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        return true
    }
}
