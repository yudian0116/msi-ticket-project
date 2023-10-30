// actions.js
export const addToCart = (item) => {
    return {
        type: 'ADD_TO_CART',
        item,
    };
};

export const toggleCartVisibility = () => {
    return {
        type: 'TOGGLE_CART_VISIBILITY',
    };
};

export const clearCart = () => {
    return {
        type: 'CLEAR_CART',
    };
};