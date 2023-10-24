import React from 'react';
import {Menu} from "antd";
import {UserOutlined, SketchOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";
import {appConstants} from "../shared/constants";

const Topbar = () => {
    return (
        <header>
            <Menu mode="horizontal" theme="light">
                <Menu.Item key="logo" icon={<SketchOutlined />} >
                    <Link to={appConstants.mainPageRoute} style={{ textDecoration: 'none' }}>MSI-Ticket</Link>
                </Menu.Item>
                <Menu.Item key="venues">
                    <Link to={appConstants.venueRoute} style={{ textDecoration: 'none' }}>Venues</Link>
                </Menu.Item>
                <Menu.Item key="events">
                    <Link to={appConstants.eventRoute} style={{ textDecoration: 'none' }}>Events</Link>
                </Menu.Item>
                <Menu.Item key="user" className={"ms-auto"}>
                    <Link to={appConstants.userMainRoute} style={{ textDecoration: 'none' }}><UserOutlined /></Link>
                </Menu.Item>
            </Menu>
        </header>
    );

}

export default Topbar;