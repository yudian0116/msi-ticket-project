import React from "react";
import BackgroundImageComponent from "../shared/Background";
import UpcomingEvents from "./UpcomingEvents";
import {Col, Divider, Row} from "antd";
import {useNavigate} from "react-router-dom";
import {appConstants} from "../shared/constants";

const MainPage = () => {

    const imageUrl = 'https://t3.ftcdn.net/jpg/06/21/61/98/360_F_621619897_1tlbTwspM9NmoylaaUpvtkDeTbb0PlQz.jpg';

    const venueImageUrl = 'https://musictech.com/wp-content/uploads/2023/07/sphere-las-vegas@2000x1500.jpg';

    const eventImageUrl = 'https://americafilm.org/wp-content/uploads/2023/01/gallery.jpg';

    const userImageUrl = 'https://camo.envatousercontent.com/e7c71546b53e7cfac011d3a8b98d4579290a6f4a/68747470733a2f2f696d6167652e6962622e636f2f6748747a65532f312e6a7067';

    const navigate = useNavigate();

    const handleClickVenues = () => {
        navigate(appConstants.venueRoute);
    };

    const handleClickEvents = () => {
        navigate(appConstants.eventRoute);
    }

    return (
        <>
            <BackgroundImageComponent imageUrl={imageUrl} isHeader={true}>
                <h1>MSI-Ticket</h1>
            </BackgroundImageComponent>
            <div style={{paddingTop: 35}} />
            <Row justify="space-around" align="middle">
                <Col span={8} onClick={handleClickVenues} style={{display: "flex", justifyContent:"center", paddingLeft:"15px", paddingRight:"15px"}}>
                    <BackgroundImageComponent imageUrl={venueImageUrl}>
                        <h4>Venues</h4>
                    </BackgroundImageComponent>
                </Col>
                <Col span={8} onClick={handleClickEvents} style={{display: "flex", justifyContent:"center", paddingLeft:"15px", paddingRight:"15px"}}>
                    <BackgroundImageComponent imageUrl={eventImageUrl}>
                        <h4>Events</h4>
                    </BackgroundImageComponent>
                </Col>
                <Col span={8} style={{display: "flex", justifyContent:"center", paddingLeft:"15px", paddingRight:"15px"}}>
                    <BackgroundImageComponent imageUrl={userImageUrl}>
                        <h4>User Center</h4>
                    </BackgroundImageComponent>
                </Col>
            </Row>
            <div style={{paddingTop: 35}} />
            <Divider orientation="left" orientationMargin="0">Upcoming Events</Divider>
            <UpcomingEvents />
        </>
    );
}

export default MainPage;