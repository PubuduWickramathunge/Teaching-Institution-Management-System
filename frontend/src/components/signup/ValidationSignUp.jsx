export const validationRules = {
  firstName: [
    {
      required: true,
      message: "Please enter your first name",
    },
    {
      whitespace: true,
      message: "First name cannot be empty",
    },
    {
      pattern: /^[A-Za-z\s]+$/,
      message: "First name should only contain alphabets and spaces",
    },
  ],

  lastName: [
    {
      required: true,
      message: "Please enter your last name",
    },
    {
      whitespace: true,
      message: "First name cannot be empty",
    },
    {
      pattern: /^[A-Za-z\s]+$/,
      message: "Last name should only contain alphabets and spaces",
    },
  ],

  email: [
    {
      required: true,
      message: "Please enter your email address",
    },
    {
      type: "email",
    },
  ],

  role: [
    {
      required: true,
      message: "Please enter a user type",
    },
  ],

  password: [
    {
      required: true,
      message: "Please enter a password",
    },

    {
      validator: (_, value) => {
        if (!value) {
          return Promise.resolve();
        }

        const lowercaseRegex = /[a-z]/;
        const uppercaseRegex = /[A-Z]/;
        const numberRegex = /[0-9]/;
        //   const specialCharRegex = /[#?!@$%^&*-]/;
        const lengthCheckerRegex = /^.{8,}$/;

        if (
          lowercaseRegex.test(value) &&
          uppercaseRegex.test(value) &&
          numberRegex.test(value) &&
          // specialCharRegex.test(value) &&
          lengthCheckerRegex.test(value)
        ) {
          return Promise.resolve();
        }
        return Promise.reject(
          <code>
            password must have more than 8
            <br />
            characters including uppercase,
            <br />
            lowercase letters and numbers
            <br />
          </code>
        );
      },
    },
  ],

  confirmPassword: [
    {
      required: true,
      message: "Reenter your password",
    },
    ({ getFieldValue }) => ({
      dependencies: ["password"],
      validator(_, value) {
        if (!value || getFieldValue("password") === value) {
          return Promise.resolve();
        }
        return Promise.reject("Passwords do not match");
      },
    }),
  ],
};
