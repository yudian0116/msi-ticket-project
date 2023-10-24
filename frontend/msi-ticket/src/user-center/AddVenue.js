import React from "react";
import {Button, Form, Input, Upload} from "antd";
import { PlusOutlined } from '@ant-design/icons';

const normFile = (e) => {
    if (Array.isArray(e)) {
        return e;
    }
    return e?.fileList;
};

const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};



const AddVenue = () => {
    const [form] = Form.useForm();
    const onReset = () => {
        form.resetFields();
    };

    return (
        <Form
            labelCol={{ span: 8 }}
            wrapperCol={{ span: 16 }}
            layout="horizontal"
            style={{ maxWidth: 600 }}
            form={form}
            scrollToFirstError
        >
            <Form.Item label="Name" name="venueName"
                       rules={[
                           {
                               required: true,
                               message: 'Please input venue\' name!',
                           },
                       ]}
                       hasFeedback>
                <Input />
            </Form.Item>
            <Form.Item label="Address" name="venueAddress"
                       rules={[
                           {
                               required: true,
                               message: 'Please input venue\' address!',
                           },
                       ]}
                       hasFeedback>
                <Input />
            </Form.Item>
            <Form.Item label="Owner">
                <Input />
            </Form.Item>
            <Form.Item label="Phone">
                <Input />
            </Form.Item>
            <Form.Item label="Image" valuePropName="fileList" getValueFromEvent={normFile}>
                <Upload action="/upload.do" listType="picture-card">
                    <div>
                        <PlusOutlined />
                        <div style={{ marginTop: 8 }}>Upload</div>
                    </div>
                </Upload>
            </Form.Item>
            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit" style={{marginRight: "8px"}}>
                    Submit
                </Button>
                <Button htmlType="button" onClick={onReset}>
                    Reset
                </Button>
            </Form.Item>
        </Form>
    );
}

export default AddVenue;