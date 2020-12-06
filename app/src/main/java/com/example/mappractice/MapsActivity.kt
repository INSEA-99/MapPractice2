package com.example.mappractice

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 확대/ 축소 가능하게
        mMap.uiSettings.isZoomControlsEnabled = true

        //마커 아이콘 만들기
        val descriptor = getDescriptorFromDrawable(R.drawable.busstop_icon3)

        // Add a marker in Sydney and move the camera
        val busStop1 = LatLng(37.240284, 127.082447)
        val busStop2 = LatLng(37.243318, 127.080756)
        val busStop3 = LatLng(37.244406, 127.079184)
        val busStop4 = LatLng(37.247751, 127.077406)

        //마커
        val marker1 = MarkerOptions().position(busStop1)
                .title("사색의광장")
                .snippet("버스 정류장: 9, 1112, 5100, 7000")
                .icon(descriptor)
        val marker2 = MarkerOptions().position(busStop2)
            .title("생명과학대.산업대학")
            .snippet("버스 정류장: 9, 1112, 5100, 7000")
            .icon(descriptor)
        val marker3 = MarkerOptions().position(busStop3)
            .title("경희대체육대학.외대")
            .snippet("버스 정류장: 9, 1112, 5100, 7000")
            .icon(descriptor)
        val marker4 = MarkerOptions().position(busStop4)
            .title("경희대학교")
            .snippet("버스 정류장: 9, 1112, 5100, 7000")
            .icon(descriptor)

        // 지도에 마커 추가
        mMap.addMarker(marker1)
        mMap.addMarker(marker2)
        mMap.addMarker(marker3)
        mMap.addMarker(marker4)

        //카메라의 초기 위치 설정
        val cameraOptions = CameraPosition.Builder().target(busStop1).zoom(18f).build()
        val camera = CameraUpdateFactory.newCameraPosition((cameraOptions))
        mMap.moveCamera(camera)


        // windowInfo 클릭 시 이동동
       mMap.setOnInfoWindowClickListener(OnInfoWindowClickListener { marker ->

            if (marker.title == "사색의광장") {
                val i = Intent(this@MapsActivity, List::class.java)
                startActivity(i)
            }
            else if (marker.title == "생명과학대.산업대학") {
                val i = Intent(this@MapsActivity, List::class.java)
                startActivity(i)
            }
            else if (marker.title == "경희대체육대학.외대") {
                val i = Intent(this@MapsActivity, List::class.java)
                startActivity(i)
            }
            else if (marker.title == "경희대학교") {
                val i = Intent(this@MapsActivity, List::class.java)
                startActivity(i)
            }
        })
    }


    // 마커 사이즈 변환
    fun getDescriptorFromDrawable(drawableId: Int) : BitmapDescriptor {
        var bitmapDrawable:BitmapDrawable
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmapDrawable = getDrawable(drawableId) as BitmapDrawable
        } else {
            bitmapDrawable = resources.getDrawable(drawableId) as BitmapDrawable
        }

        // 마커 크기 변환
        val scaledBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }

}

