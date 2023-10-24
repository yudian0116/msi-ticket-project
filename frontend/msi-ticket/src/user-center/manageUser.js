import React from 'react';
import {Button, Space, Table, Tag} from 'antd';
const { Column } = Table;
const data = [
    {
        key: '1',
        id: '1',
        email: '123@123.com',
        role: ['Customer'],
    },
    {
        key: '2',
        id: '2',
        email: 'admin@123.com',
        role: ['Admin'],
    },
];
const ManageUser = () => (
    <Table dataSource={data}>
        <Column title="Id" dataIndex="id" key="id" />
        <Column title="Email" dataIndex="email" key="email" />
        <Column
            title="Role"
            dataIndex="role"
            key="role"
            render={(role) => (
                <>
                    {role.map((tag) => (
                        tag === 'Admin' ? <Tag color="red" key={tag}>
                                {role}
                            </Tag>
                        :
                        <Tag color="blue" key={tag}>
                            {role}
                        </Tag>
                    ))}
                </>
            )}
        />
        <Column
            title="Action"
            key="action"
            render={() => (
                <Space size="small">
                    <Button type="link">Activate</Button>
                    <Button type="link">Deactivate</Button>
                </Space>
            )}
        />
    </Table>
);
export default ManageUser;