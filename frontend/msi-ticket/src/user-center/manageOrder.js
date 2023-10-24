import React from 'react';
import {Button, Space, Table, Tag} from 'antd';

const ManageOrder = () => {
    const expandedRowRender = () => {
        const columns = [
            {
                title: 'Ticket',
                dataIndex: 'ticket',
                key: 'ticket',
            },
            {
                title: 'Price',
                dataIndex: 'price',
                key: 'price',
            },
            {
                title: 'Quantity',
                dataIndex: 'quantity',
                key: 'quantity',
            },
            {
                title: 'Subtotal',
                dataIndex: 'subtotal',
                key: 'subtotal',
            },
        ];
        const data = [{ticket: '1', price: '100', quantity: '1', subtotal: '100'}, {ticket: '2', price: '50', quantity: '1', subtotal: '50'}];

        return <Table columns={columns} dataSource={data} pagination={false} />;
    };
    const columns = [
        {
            title: 'Order ID',
            dataIndex: 'orderId',
            key: 'orderId',
        },
        {
            title: 'Created At',
            dataIndex: 'createdAt',
            key: 'createdAt',
        },
        {
            title: 'User',
            dataIndex: 'userId',
            key: 'userId',
        },
        {
            title: 'Total',
            dataIndex: 'total',
            key: 'total',
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
            render: (status) => (
                <>
                    {status.map((tag) => (
                        <Tag color="blue" key={tag}>
                            {status}
                        </Tag>
                    ))}
                </>
            ),
        },

        {
            title: 'Action',
            key: 'operation',
            render: () =>
                <Space size="small">
                    <Button type="link">Complete</Button>
                    <Button type="link">Cancel</Button>
                </Space>
                ,
        },
    ];
    const data = [{createdAt: '2023-10-18 23:12:00', orderId: '1', userId: '1', total: '150', status: ['Complete']}];

    return (
        <Table
            columns={columns}
            expandable={{
                expandedRowRender,
                defaultExpandedRowKeys: ['0'],
            }}
            dataSource={data}
        />
    );
};
export default ManageOrder;