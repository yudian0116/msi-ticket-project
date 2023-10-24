import React from "react";
import {Button, DatePicker, Form, Input, InputNumber, Select} from "antd";

const tailLayout = {
    wrapperCol: { offset: 8, span: 16 },
};



const AddTicket = () => {
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
            <Form.Item label="Event" name="ticketEvent"
                       rules={[
                           {
                               required: true,
                               message: 'Please select event!',
                           },
                       ]}
                       hasFeedback>
                <Select>
                    <Select.Option value="eve1">Event 1</Select.Option>
                    <Select.Option value="eve2">Event 2</Select.Option>
                </Select>
            </Form.Item>
            <Form.Item label="Type">
                <Input />
            </Form.Item>
            <Form.Item label="Price" name="ticketPrice"
                       rules={[
                           {
                               required: true,
                               message: 'Please input ticket\'s price!',
                           },
                       ]}
                       hasFeedback>
                <InputNumber formatter={(value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                             parser={(value) => value.replace(/\$\s?|(,*)/g, '')} />
            </Form.Item>
            <Form.Item label="Stock" name="ticketStocek"
                       rules={[
                           {
                               required: true,
                               message: 'Please input ticket\'s stock!',
                           },
                       ]}
                       hasFeedback>
                <InputNumber />
            </Form.Item>
            <Form.Item label="Dates" name="ticektDate">
                <DatePicker />
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

export default AddTicket;