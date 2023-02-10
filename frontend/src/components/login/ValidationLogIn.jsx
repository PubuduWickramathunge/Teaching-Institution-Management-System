import { message } from "antd";

export const validationRules = {
    
    
    email: [
        {
            required: false,
            message: "Please enter your email address",
        },
        {
            type: "email",
            message: "Please check your email address"
        }

    ],
    
    
};
