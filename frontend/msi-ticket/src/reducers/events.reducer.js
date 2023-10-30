
const events = [{"id":1,"name":"Yuki's Solo Concert","startTime":"2023-10-16T03:30:00Z","endTime":"2023-10-16T06:30:00Z","category":{"id":1,"name":"concert"},"image":"https://cdn.choosechicago.com/uploads/2019/06/AA_EPIC_TheHideout_03_5d609e69-8be5-4bd1-b139-9bb8d3ec22c9.jpg","description":"Placeholder", "ticketList": [{"id":1,"price":50.0,"type":"VIP"},{"id":2,"price":25.0,"type":"Common"}]},
   {"id":2,"name":"Art Exhibit","startTime":"2023-10-16T03:30:00Z","endTime":"2023-10-16T06:30:00Z","category":{"id":2,"name":"art"},"image":"https://www.ut.edu/uploadedImages/_News/2021/ASJAE.JPG","description":"Placeholder", "ticketList": [{"id":3,"price":30.0,"type":"General"}]},];

export const eventsReducer = (state = events, action) => {
    switch (action.type) {

        default:
            return state;
    }
};