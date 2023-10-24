import './App.css';
import Header from "./header/TopBar";
import {Layout} from "antd";
import Topbar from "./header/TopBar";
import {Content, Footer} from "antd/es/layout/layout";
import ShoppingCart from "./cart/shoppingCart";

function App(props) {
  return (
    <Layout>
        <Header style={{
            position: 'sticky',
            top: 0,
            zIndex: 1,
            width: '100%',
            display: 'flex',
            alignItems: 'center',
        }}>
            <Topbar />
        </Header>
        <Content className="site-layout"
                 style={{
                     padding: '0 50px',
                     paddingTop: 15,
                 }}
        >
            {props.children}
            <ShoppingCart />
        </Content>
        <Footer
            style={{
                textAlign: 'center',
            }}
        >
            MSI Ticket ©2023 Created by YL
        </Footer>
    </Layout>
  );
}

export default App;
