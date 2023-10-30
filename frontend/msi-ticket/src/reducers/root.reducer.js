import {combineReducers} from "redux";
import {venuesReducer} from "./venues.reducer";
import {eventsReducer} from "./events.reducer";
import {cartReducer} from "./cart.reducer";

export const rootReducer = combineReducers({
   venues: venuesReducer,
   events: eventsReducer,
   cart: cartReducer,

});