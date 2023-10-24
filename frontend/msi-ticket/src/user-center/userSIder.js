import React from 'react';
import {Menu} from "antd";
import { UserOutlined, PlusOutlined, ContainerOutlined, TeamOutlined} from "@ant-design/icons";
import {Link} from "react-router-dom";
import {appConstants} from "../shared/constants";
import SubMenu from "antd/lib/menu/SubMenu";

const UserSider = () => {
    return (
            <Menu mode="inline" theme="light">
                <Menu.Item key="changePW" icon={<UserOutlined />} >
                    <Link to={appConstants.changePW} style={{ textDecoration: 'none' }}>Change Password</Link>
                </Menu.Item>
                <Menu.Item key="manageOrders" icon={<ContainerOutlined />}>
                    <Link to={appConstants.manageOrder} style={{ textDecoration: 'none' }}>Manage Order</Link>
                </Menu.Item>
                <Menu.Item key="manageUsers" icon={<TeamOutlined />}>
                    <Link to={appConstants.manageUser} style={{ textDecoration: 'none' }}>Manage User</Link>
                </Menu.Item>
                <SubMenu key="addMore" icon={<PlusOutlined />} title="Add More">
                    <Menu.Item>
                        <Link to={appConstants.addVenueForm} style={{ textDecoration: 'none' }}>Add Venue</Link>
                    </Menu.Item>
                    <Menu.Item>
                        <Link to={appConstants.addEventForm} style={{ textDecoration: 'none' }}>Add Event</Link>
                    </Menu.Item>
                    <Menu.Item>
                        <Link to={appConstants.addTicketForm} style={{ textDecoration: 'none' }}>Add Ticket</Link>
                    </Menu.Item>
                </SubMenu>

            </Menu>
    );

}

export default UserSider;