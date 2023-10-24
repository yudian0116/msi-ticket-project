import React from 'react';
import BackgroundImageComponent from "../shared/Background";
import TicketList from "./TicketList";
import {connect} from "react-redux";
import {useParams} from "react-router-dom";

const pacificTimeOptions = {
    timeZone: "America/Los_Angeles",
    hour12: false,
    year: "numeric",
    month: "numeric",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    second: "numeric",
    timeZoneName: "short"
};

const EventDetail = (props) => {
    const { id } = useParams(); // Extract the eventId from the URL parameter

    const event = props.events.find((event) => event.id === parseInt(id));

    return(
        <div>
            <BackgroundImageComponent imageUrl={event.image} isHeader={true}>
                <h1>{event.name}</h1>
                <h4>Venue</h4>
                <p>{Intl.DateTimeFormat("en-US", pacificTimeOptions).format(new Date(event.startTime))} to {Intl.DateTimeFormat("en-US", pacificTimeOptions).format(new Date(event.endTime))}</p>
            </BackgroundImageComponent>
            <TicketList />
        </div>
)};

function MapStateToProps(reduxState) {
    // the returned object will be merged with your component props
    return {
        events: reduxState.events
    };
}

export default connect(MapStateToProps)(EventDetail);