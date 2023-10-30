import React from 'react';
import { connect } from 'react-redux';
import {Drawer, Button, Divider, FloatButton, List} from 'antd';
import {ShoppingCartOutlined} from "@ant-design/icons";
import {clearCart} from "../actions/cart.action";
import {useNavigate} from "react-router-dom";
import {appConstants} from "../shared/constants";

const ShoppingCart = ({ cartItems, isCartVisible, toggleCartVisibility, clearCart }) => {

    const navigate = useNavigate();

    const handleCheckout = () => {

        // Redirect to the checkout page
        navigate(appConstants.checkoutRoute);
    };

        // Calculate the total price of all items in the cart
        const totalCartPrice = cartItems.reduce((total, cartItem) => {
            const itemPrice = cartItem.tickets.reduce(
                (itemTotal, ticket) => itemTotal + ticket.price * ticket.quantity,
                0
            );
            return total + itemPrice;
        }, 0);

        return (
        <>
            <FloatButton type="primary" onClick={toggleCartVisibility} icon={<ShoppingCartOutlined />} />
            <Drawer
                title="Shopping Cart"
                placement="right"
                width={300}
                onClose={toggleCartVisibility}
                open={isCartVisible}
                style={{ height: '100%' }}
            >
                <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                    <div style={{ flex: 1, overflowY: 'auto' }}>
                        {cartItems.map((cartItem) => (
                            <div key={cartItem.event.id}>
                                <h3>{cartItem.event.name}</h3>
                                <List
                                    itemLayout="horizontal"
                                    size="large"
                                    dataSource={cartItem.tickets}
                                    renderItem={(ticket) => (
                                        <List.Item key={ticket.id}>
                                            <List.Item.Meta
                                                title={ticket.type}
                                                description={`Price: $${ticket.price}, Quantity: ${ticket.quantity}`}
                                            />
                                        </List.Item>
                                    )}
                                />
                            </div>
                        ))}
                    </div>

                    <div style={{ flex: 'none', position: 'fixed', bottom: '0', width: '100%' }}>
                        <Divider />
                        <div style={{ textAlign: 'left', marginBottom: '8px' }}>
                            Total Price: $ {totalCartPrice.toFixed(2)}
                        </div>
                        <div style={{ paddingBottom: "8px", marginTop: "8px" }}>
                            {cartItems.length > 0 ? (
                                <>
                            <Button type="primary" onClick={handleCheckout} style={{ marginRight: "8px", marginTop:"8px"}}>
                                Checkout
                            </Button>
                            <Button type="default" onClick={clearCart}>
                                Clear
                            </Button>
                                </>
                                ) : (
                                    <>
                                    <Button type="primary" disabled style={{ marginRight: "8px", marginTop:"8px"}}>
                                    Checkout</Button>
                                        <Button type="default" onClick={clearCart}>
                                            Clear
                                        </Button>
                                    </>)
                        }
                        </div>

                    </div>
                </div>
            </Drawer>
        </>
    );
};

const mapStateToProps = (state) => ({
    cartItems: state.cart.items,
    isCartVisible: state.cart.isCartVisible,
});

const mapDispatchToProps = (dispatch) => ({
    toggleCartVisibility: () => dispatch({ type: 'TOGGLE_CART_VISIBILITY' }),
    clearCart: () => dispatch(clearCart()),
});

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingCart);
