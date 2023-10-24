import React from 'react';
import {Button, List} from "antd";
import {ShoppingCartOutlined} from "@ant-design/icons";

const TicketList = () => {
    return (
        <List itemLayout="horizontal" size="large">
            <List.Item>
                <List.Item.Meta
                    title="Day 1"
                    description="Common ticket"
                />
                <div style={{paddingRight: 75}}><p style={{fontSize: 17, paddingTop:14}}>Price: $10</p></div>
                <div className="ms-auto">
                    <Button type="default" icon={<ShoppingCartOutlined />}>Add to cart</Button>
                </div>
            </List.Item>
            <List.Item>
                <List.Item.Meta
                    title="Day 1"
                    description="VIP ticket"
                />
                <div style={{paddingRight: 75}}><p style={{fontSize: 17, paddingTop:14}}>Price: $50</p></div>
                <div className="ms-auto">
                    <Button type="default" icon={<ShoppingCartOutlined />}>Add to cart</Button>
                </div>
            </List.Item>
        </List>
    )};

export default TicketList;