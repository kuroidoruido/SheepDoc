# SheepDoc

A simple tool to fill docx templates.

## Usage

- create .docx file with placeholders indicated like this:

```text
This sentence contains a ${placeholder}.
```

A placeholder can contains only letters (upper or lower case), number, "_" and "-".

- launch SheepDoc
- it will ask you to select a template file
- it will scan to discover placeholders
- you will be able to asign a value for each placeholder
- when clicking on Generate button, it will ask you for output file name and save generated document

## Limitation

For now SheepDoc only support replacing placeholders in paragraphs. Tables are not supported.

## Technologies

- Java 8
- Swing
- Apache POI

## Licence

GNU GENERAL PUBLIC LICENSE Version 3