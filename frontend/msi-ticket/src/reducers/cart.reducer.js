const initialState = {items:[], isCartVisible: false};

export const cartReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'ADD_TO_CART':
            const newItem = action.item;
            const existingItemIndex = state.items.findIndex(
                (item) => item.event.id === newItem.event.id
            );

            if (existingItemIndex !== -1) {
                const existingItem = state.items[existingItemIndex];

                const existingTicketIndex = existingItem.tickets.findIndex(
                    (ticket) => ticket.id === newItem.ticket.id
                );

                if (existingTicketIndex !== -1) {
                    // If the same ticket is already in the cart, increase the quantity.
                    existingItem.tickets[existingTicketIndex].quantity += 1;
                } else {
                    // Otherwise, add a new ticket type to the existing event.
                    newItem.ticket.quantity = 1;
                    existingItem.tickets.push(newItem.ticket);
                }
            } else {
                // Otherwise, add a new cart item.
                newItem.ticket.quantity = 1;
                newItem.tickets = [newItem.ticket];
                state.items.push(newItem);
            }

            return {
                ...state,
                items: [...state.items],
            };

        case 'CLEAR_CART':
            return {
                ...state,
                items: [],
            };

        case 'TOGGLE_CART_VISIBILITY':
            return {
                ...state,
                isCartVisible: !state.isCartVisible,
            };

        default:
            return state;
    }
};
