# library catalog processor 📚
developed a system to validate, categorize, and manage a large-scale book catalog with automated error detection and file-based record handling. the system features an interactive user interface that allows users to navigate through their a catalog collection seamlessly.

## features 👾
- **interactive navigation:** users can easily browse through categorized book titles by genre, with options to view details and manage records interactively.
- **book validation:** automatically checks for syntax and semantic errors in book records, ensuring data integrity and accuracy while identifying and reporting errors such as missing fields or invalid data formats with detailed error messages.
- **categorization:** organizes books into predefined genres, making it easy to find and manage large collections.
- **file handling:** supports reading from and writing to csv and binary files, facilitating easy data import and export.
- **dynamic reporting:** generates error logs for both syntax and semantic issues, informing users on data entry mistakes.
- **serialization:** converts valid book records into binary format for efficient storage and retrieval.

## running the project 🏁
to get the project up and running on your local machine, follow these steps:

- **ensure jdk is installed:** must have the [latest jdk](https://www.java.com/en/download/manual.jsp) installed.
- **clone the repository:**
```bash
git clone https://github.com/barbaraeguche/library-catalog-processor.git
```
- **navigate to the project directory:**
```bash
cd library-catalog-processor
cd src
```
- **run the project:**
```bash
javac Library.java
java Library
```

## gallery 📸
<details>
  <summary>showcase</summary>

  - **main menu**
    <img width="1368" alt="main-s" src="https://github.com/user-attachments/assets/9b3286da-1aee-4d78-a873-7e787960e818">
    <img width="1368" alt="main-x" src="https://github.com/user-attachments/assets/6b7e2585-fb05-49c0-8c85-a3ff0147612d">

  - **sub menu**
    <img width="1368" alt="sub" src="https://github.com/user-attachments/assets/51431697-74d6-45d9-8007-7aa13a7cbbf1">

  - **catalog viewing**
    <img width="1368" alt="viewing" src="https://github.com/user-attachments/assets/6b113eae-965a-4a93-acbc-eebafcab323b">

  - **catalog details**
    <img width="1368" alt="details" src="https://github.com/user-attachments/assets/322ccebd-ecf2-4b46-842a-b3256a22f4ae">
</details>
