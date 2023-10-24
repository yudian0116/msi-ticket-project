import React from "react";
import {Button, DatePicker, Form, Input, Select, Upload} from "antd";
import { PlusOutlined } from '@ant-design/icons';

const { RangePicker } = DatePicker;
const normFile = (e) => {
    if (Array.isArray(e)) {
        return e;
    }
    return e?.fileList;
};

const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};



const AddEvent = () => {
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
            <Form.Item
                name="eventName"
                label="Name"
                       rules={[
                           {
                               required: true,
                               message: 'Please input event name!',
                           },
                       ]}
                       hasFeedback>
                <Input />
            </Form.Item>
            <Form.Item label="Description">
                <Input />
            </Form.Item>
            <Form.Item label="Category">
                <Select>
                    <Select.Option value="cat1">Concert</Select.Option>
                    <Select.Option value="cat2">Exhibition</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item label="Venue" name="eventVenue"
                       rules={[
                           {
                               required: true,
                               message: 'Please select venue!',
                           },
                       ]}
                       hasFeedback>
                <Select>
                    <Select.Option value="venue1">Venue 1</Select.Option>
                    <Select.Option value="venue2">Venue 2</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item label="Dates" name="dateTime"
                       rules={[
                           {
                               required: true,
                               message: 'Please select event\' data and time!',
                           },
                       ]}
                       hasFeedback>
                <RangePicker showTime={{format: 'HH:mm',}} format="YYYY-MM-DD HH:mm" />
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

export default AddEvent;