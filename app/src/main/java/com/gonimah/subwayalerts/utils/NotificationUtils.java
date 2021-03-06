package com.gonimah.subwayalerts.utils;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.gonimah.subwayalerts.R;
import com.gonimah.subwayalerts.models.TravelInformation;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NotificationUtils {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("HH:mm");

    public static Notification createNotification(Context context, TravelInformation travelInformation) {
        DateTime now = DateTime.now();
        DateTime timeToLeave = travelInformation.getTrainTime().minusMinutes(travelInformation.getSubwayStationCommuteTimeInMin());
        Period diff = new Period(now, timeToLeave);

        String title = String.format("Leave in %d minutes!", diff.getMinutes());
        String body = String.format("Walk to %s and take the %s train arriving at %s.",
                travelInformation.getSubwayStationName(), travelInformation.getTrainName(),
                travelInformation.getTrainTime().toString(DATE_FORMAT));

        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.map_icon)
                .setContentTitle(title)
                .setContentText(body)
                .build();
    }

    private NotificationUtils() { }
}
