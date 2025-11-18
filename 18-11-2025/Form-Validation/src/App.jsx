import { useState } from "react";

export default function App() {
  const [form, setForm] = useState({
    name: "",
    role: "",
    gender: "",
    terms: false,
    date: "",
    password: "",
  });

  const [errors, setErrors] = useState({});

  const passwordRules = {
    length: form.password.length >= 8,
    uppercase: /[A-Z]/.test(form.password),
    lowercase: /[a-z]/.test(form.password),
    number: /[0-9]/.test(form.password),
    special: /[^A-Za-z0-9]/.test(form.password),
  };

  const validate = () => {
    const newErrors = {};

    if (!form.name) newErrors.name = "Name is required.";
    if (!form.role) newErrors.role = "Role is required.";
    if (!form.gender) newErrors.gender = "Gender is required.";
    if (!form.date) newErrors.date = "Date is required.";
    if (!form.terms) newErrors.terms = "You must agree to the terms.";

    if (!Object.values(passwordRules).every(Boolean)) {
      newErrors.password = "Password does not meet requirements.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === "checkbox" ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      alert("Form submitted successfully!");
    }
  };

  return (
    <div style={{ padding: 30 }}>
      <h1>React Form Validation</h1>

      <form onSubmit={handleSubmit}>
        {/* Text Field */}
        <label>Name:</label><br />
        <input name="name" value={form.name} onChange={handleChange} />
        <p style={{ color: "red" }}>{errors.name}</p>

        {/* Dropdown */}
        <label>Role:</label><br />
        <select name="role" value={form.role} onChange={handleChange}>
          <option value="">-- Select Role --</option>
          <option value="admin">Admin</option>
          <option value="user">User</option>
        </select>
        <p style={{ color: "red" }}>{errors.role}</p>

        {/* Radio Buttons */}
        <label>Gender:</label><br />
        <label><input type="radio" name="gender" value="male" onChange={handleChange} /> Male</label><br />
        <label><input type="radio" name="gender" value="female" onChange={handleChange} /> Female</label>
        <p style={{ color: "red" }}>{errors.gender}</p>

        {/* Date */}
        <label>Date:</label><br />
        <input type="date" name="date" value={form.date} onChange={handleChange} />
        <p style={{ color: "red" }}>{errors.date}</p>

        {/* Password */}
        <label>Password:</label><br />
        <input type="password" name="password" value={form.password} onChange={handleChange} />
        <p style={{ color: "red" }}>{errors.password}</p>

        {/* Terms */}
        <label>
          <input type="checkbox" name="terms" checked={form.terms} onChange={handleChange} />
          I agree to the terms
        </label>
        <p style={{ color: "red" }}>{errors.terms}</p>

        <button type="submit" style={{ marginTop: 20 }}>
          Submit
        </button>
      </form>
    </div>
  );
}
