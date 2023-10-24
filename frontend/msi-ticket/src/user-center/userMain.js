import React, { useState } from 'react';
import { Layout, theme } from 'antd';
import UserSider from "./userSIder";
import {Outlet} from "react-router-dom";

const { Content, Sider } = Layout;

const UserMain = () => {
    const [collapsed, setCollapsed] = useState(false);
    const {
        token: { colorBgContainer },
    } = theme.useToken();
    return (
        <Layout
            style={{
                minHeight: '100vh',
            }}
        >
            <Sider theme="light" collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                <UserSider />
            </Sider>
            <Content
                style={{
                    margin: '0 16px',
                }}
            >
                <div
                    style={{
                        padding: 24,
                        minHeight: 360,
                        background: colorBgContainer,
                    }}
                >
                    <Outlet />
                </div>
            </Content>
        </Layout>
    );
};
export default UserMain;