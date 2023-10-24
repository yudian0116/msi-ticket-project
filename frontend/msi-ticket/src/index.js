import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {appConstants} from "./shared/constants";
import Events from "./events/EventsCard";
import Venues from "./venues/VenuesCard";
import EventDetail from "./events/EventDetail";
import MainPage from "./main/MainPage";
import UserMain from "./user-center/userMain";
import ChangePW from "./user-center/ChangePW";
import AddEvent from "./user-center/AddEvent";
import AddVenue from "./user-center/AddVenue";
import AddTicket from "./user-center/AddTicket";
import Login from "./login/login";
import Register from "./login/register";
import ManageOrder from "./user-center/manageOrder";
import ManageUser from "./user-center/manageUser";
import {rootReducer} from "./reducers/root.reducer";
import {Provider} from "react-redux";
import {createStore} from "redux";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Provider store={createStore(rootReducer)}>
        <BrowserRouter>
            <App>
                <Routes>
                  <Route path={appConstants.loginRoute} element={<Login />} />
                  <Route path={appConstants.registerRoute} element={<Register />} />
                  <Route path={appConstants.mainPageRoute} element={<MainPage />} />
                  <Route path={appConstants.eventRoute} element={<Events />} />
                  <Route path={appConstants.venueRoute} element={<Venues />} />
                  <Route path={`${appConstants.eventDetailRoute}/:id`} element={<EventDetail />} />
                  <Route path={appConstants.userMainRoute} element={<UserMain />}>
                  <Route path={appConstants.changePW} element={<ChangePW />} />
                  <Route path={appConstants.addEventForm} element={<AddEvent />} />
                  <Route path={appConstants.addVenueForm} element={<AddVenue />} />
                  <Route path={appConstants.addTicketForm} element={<AddTicket />} />
                  <Route path={appConstants.manageOrder} element={<ManageOrder />} />
                  <Route path={appConstants.manageUser} element={<ManageUser />} />
                  </Route>
              </Routes>
          </App>
        </BrowserRouter>
    </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
