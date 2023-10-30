import React from 'react';
import {Form, Input, Button, Divider, Row, Col, List} from 'antd';
import { useForm, Controller } from 'react-hook-form';
import {connect} from "react-redux";
import {clearCart} from "../actions/cart.action";

const Checkout = ({cartItems, clearCart}) => {
    const { control, handleSubmit } = useForm();

    // Calculate the total price of all items in the cart
    const totalCartPrice = cartItems.reduce((total, cartItem) => {
        const itemPrice = cartItem.tickets.reduce(
            (itemTotal, ticket) => itemTotal + ticket.price * ticket.quantity,
            0
        );
        return total + itemPrice;
    }, 0);

    const onSubmit = (data) => {
        // Handle the form submission, e.g., send data to the server
        console.log(data);

        clearCart();
    };

    return (
        <>
<Row>
        <Col span={16}>
        <div style={{ display: 'flex', justifyContent: 'center', height: '100vh' }}>
            <Form layout="vertical" onFinish={handleSubmit(onSubmit)}>
                <h3>Email to Receive Your Tickets</h3>
                <Form.Item name="email" rules={[{ required: true, type: 'email', message: 'Please enter a valid email' }]}>
                    <Controller
                        name="email"
                        control={control}
                        render={({ field }) => <Input {...field} />}
                    />
                </Form.Item>
                <Divider />
                <h3>Payment Information</h3>
                <Form.Item label="Card Number" name="cardNumber" rules={[{ required: true, message: 'Please enter the card number' }]}>
                    <Controller
                        name="cardNumber"
                        control={control}
                        render={({ field }) => <Input {...field} placeholder="Card Number" />}
                    />
                </Form.Item>
                <Form.Item label="Name on Card" name="nameOnCard" rules={[{ required: true, message: 'Please enter the name on the card' }]}>
                    <Controller
                        name="nameOnCard"
                        control={control}
                        render={({ field }) => <Input {...field} placeholder="Name on Card" />}
                    />
                </Form.Item>
                <Row gutter={16}>
                <Col span={12}>
                <Form.Item label="Expiration Date" name="expirationDate" rules={[{ required: true, message: 'Please enter the expiration date' }]}>
                    <Controller
                        name="expirationDate"
                        control={control}
                        render={({ field }) => <Input {...field} placeholder="MM/YY" />}
                    />
                </Form.Item>
                </Col>
                <Col span={12}>
                <Form.Item label="CVV" name="cvv" rules={[{ required: true, message: 'Please enter the CVV' }]}>
                    <Controller
                        name="cvv"
                        control={control}
                        render={({ field }) => <Input {...field} placeholder="CVV" />}
                    />
                </Form.Item>
                </Col>
                </Row>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>

        </div>
        </Col>
        <Col span={8}>
            <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                <h3>Items</h3>
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
                    <Divider />
                    <div>
                        Subtotal: $ {totalCartPrice.toFixed(2)}
                    </div>
                </div>
            </div>

        </Col>
        </Row>
</>
    );
};

const mapStateToProps = (state) => ({
    cartItems: state.cart.items, // Assuming your cart items are stored in a Redux state
});

const mapDispatchToProps = (dispatch) => ({
    clearCart: () => dispatch(clearCart()), // Define your clearCart action
});


export default connect(mapStateToProps, mapDispatchToProps)(Checkout);
