import React from 'react';
import {Card, Col, Row} from 'antd';
import Search from "antd/lib/input/Search";
import {connect} from "react-redux";

const { Meta } = Card;
const Venues = (props) => (
    <>
        <div style={{paddingBottom: 15}}>
            <Search placeholder="input search text" size="large" />
        </div>
        <Row gutter={[16, 16]}>
            {props.venues.map(venue => (
                <Col span={8}>
                    <Card bordered={false} cover={<img height="250px" alt="example" src={venue.image} />}>
                        <Meta title={venue.name} description={venue.address} />
                    </Card>
                </Col>
            ))}
        </Row>
    </>
);

function MapStateToProps(reduxState) {
    // the returned object will be merged with your component props
    return {
        venues: reduxState.venues
    };
}

export default connect(MapStateToProps)(Venues);