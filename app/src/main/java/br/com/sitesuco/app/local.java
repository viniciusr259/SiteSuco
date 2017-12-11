package br.com.sitesuco.app;

import static android.R.attr.id;

/**
 * Created by 16254866 on 11/12/2017.
 */

public class local {

    Long latitude;
    Long longitude;

    public static local create(Long latitude, Long longitude){
        local p = new local();
        p.setLatitude(latitude);
        p.setLongitude(longitude);

        return p;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }


}
