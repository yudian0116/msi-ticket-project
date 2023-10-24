import React from 'react';
import {Button, List} from "antd";
import {appConstants} from "../shared/constants";
import {useNavigate} from "react-router-dom";

const UpcomingEvents = () => {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate(appConstants.eventDetailRoute);
    };

    return (
        <List itemLayout="horizontal" size="large">
            <List.Item>
                <List.Item.Meta
                    title="Concert"
                    description="Oct 9 - Oct 10"
                />
                <div className="ms-auto">
                    <Button type="default" onClick={handleClick}>See Detail</Button>
                </div>
            </List.Item>
            <List.Item>
                <List.Item.Meta
                    title="Art Exhibit"
                    description="Oct 11 - Oct 12"
                />
                <div className="ms-auto">
                    <Button type="default">See Detail</Button>
                </div>
            </List.Item>
        </List>
    )};

export default UpcomingEvents;