import React from 'react';
import BackgroundImageComponent from "../shared/Background";
import {connect} from "react-redux";
import {useParams} from "react-router-dom";
import {Button, List} from "antd";
import {ShoppingCartOutlined} from "@ant-design/icons";

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
            <List
                itemLayout="horizontal"
                size="large"
                dataSource={event.ticketList}
                renderItem={(ticket) => (
                    <List.Item key={ticket.id}>
                        <List.Item.Meta
                            title={ticket.type}
                            description={null}
                        />
                        <div style={{paddingRight: 75}}><p style={{fontSize: 17, paddingTop:14}}>{"Price: $" + ticket.price}</p></div>
                        <div className="ms-auto">
                            <Button type="default" onClick={() => props.addToCart({event, ticket})} icon={<ShoppingCartOutlined />}>Add to cart</Button>
                        </div>
                    </List.Item>
                )}
            />
        </div>
)};

function mapStateToProps(reduxState) {
    // the returned object will be merged with your component props
    return {
        events: reduxState.events
    };
}

const mapDispatchToProps = (dispatch) => ({
    addToCart: (item) => dispatch({ type: 'ADD_TO_CART', item }),
});

export default connect(mapStateToProps, mapDispatchToProps)(EventDetail);