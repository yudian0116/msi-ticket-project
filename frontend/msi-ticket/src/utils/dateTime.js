import React from 'react';
import { format } from 'date-fns-tz';

const ZonedDateTimeDisplay = ({ zonedDateTime }) => {
    if (!zonedDateTime) {
        return null; // Handle null or invalid input as needed
    }

    // Format the ZonedDateTime object to a desired format
    const formattedDate = format(
        zonedDateTime,
        'dd MMM yyyy HH:mm:ss zzz', // Customize the format as you like
        {timeZone: 'America/New_York' }
    );

    return <span>{formattedDate}</span>;
};

export default ZonedDateTimeDisplay;
