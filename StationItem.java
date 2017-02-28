package org.maxvigdorchik.floodwarning;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxv0 on 2/28/2017.
 */
public class StationItem implements Parcelable
{
    public String id;
    public String risk;
    public double lat;
    public double lon;


    public StationItem(String id, String risk, double lat, double lon)
    {
        this.id = id;
        this.risk = risk;
        this.lat = lat;
        this.lon = lon;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags)
    {
        out.writeDouble(lat);
        out.writeDouble(lon);
        out.writeString(id);
        out.writeString(risk);
    }

    public static final Parcelable.Creator<StationItem> CREATOR
            = new Parcelable.Creator<StationItem>() {
        public StationItem createFromParcel(Parcel in) {
            return new StationItem(in);
        }

        public StationItem[] newArray(int size) {
            return new StationItem[size];
        }
    };

    private StationItem(Parcel in) {
        risk = in.readString();
        id = in.readString();
        lon = in.readDouble();
        lat = in.readDouble();
    }

    @Override
    public String toString()
    {
        return id + ", " + risk;
    }
}
