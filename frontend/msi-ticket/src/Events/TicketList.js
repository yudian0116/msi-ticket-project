import React from 'react';
import {Button, List} from "antd";
import {ShoppingCartOutlined} from "@ant-design/icons";
import {connect} from "react-redux";

const TicketList = ({tickets, addToCart}) => {
    return (
        <List
            itemLayout="horizontal"
            size="large"
            dataSource={tickets}
            renderItem={(ticket) => (
                <List.Item key={ticket.id}>
                    <List.Item.Meta
                        title={ticket.type}
                        description={null}
                    />
                    <div style={{paddingRight: 75}}><p style={{fontSize: 17, paddingTop:14}}>{"Price: $" + ticket.price}</p></div>
                    <div className="ms-auto">
                        <Button type="default" onClick={() => addToCart(ticket)} icon={<ShoppingCartOutlined />}>Add to cart</Button>
                    </div>
                </List.Item>
            )}
        />
    )};

const mapDispatchToProps = (dispatch) => ({
    addToCart: (item) => dispatch({ type: 'ADD_TO_CART', item }),
});

export default connect(null, mapDispatchToProps)(TicketList);