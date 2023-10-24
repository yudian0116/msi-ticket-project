import React from 'react';
import { Card, Col, Row } from 'antd';
import Search from "antd/lib/input/Search";
import {useNavigate} from "react-router-dom";
import {appConstants} from "../shared/constants";
import {connect} from "react-redux";

const { Meta } = Card;

const Events = (props) => {
    const navigate = useNavigate();

    return(
    <>
        <div style={{paddingBottom: 15}}>
            <Search placeholder="input search text" size="large" />
        </div>
        <Row gutter={[16, 16]}>
            {props.events.map(event => (
                <Col span={8}>
                    <Card hoverable key={event.id} onClick={()=> {navigate(appConstants.eventDetailRoute + '/' + event.id)}} bordered={false} cover={<img height="250px" alt="example" src={event.image} />}>
                        <Meta title={event.name} description={event.description} />
                    </Card>
                </Col>
            ))}
        </Row>
    </>
)};

function MapStateToProps(reduxState) {
    // the returned object will be merged with your component props
    return {
        events: reduxState.events
    };
}

export default connect(MapStateToProps)(Events);