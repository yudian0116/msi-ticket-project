import React from 'react';

const BackgroundImageComponent = ({isHeader, imageUrl, children}) => {

    const containerStyleHeader = {
        background: `url(${imageUrl})`,
        backgroundSize: '100%',
        backgroundPosition: 'center',
        width: '100%',
        height: '300px', // Adjust the height as needed
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
    };

    const containerStyle = {
        background: `url(${imageUrl})`,
        backgroundSize: '100%',
        backgroundPosition: 'center',
        width: '100%',
        height: '300px', // Adjust the height as needed
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        borderRadius: '15px',
    };

    const contentStyle = {
        backgroundColor: 'rgba(255, 255, 255, 0.7)', // Add a semi-transparent background if needed
        padding: '20px',
        borderRadius: '5px',
    };

    if (isHeader) {
        return (
            <div style={containerStyleHeader}>
                <div style={contentStyle}>
                    {children}
                </div>
            </div>
        );
    } else {
        return (
            <div style={containerStyle}>
                <div style={contentStyle}>
                    {children}
                </div>
            </div>
        );
    }

};

export default BackgroundImageComponent;
