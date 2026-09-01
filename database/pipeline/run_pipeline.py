import os

from excel_reader import read_timetable

from validator import (
    validate_columns,
    validate_records,
    validate_schedule
)

from transformer import transform_records

from mysql_loader import import_timetable

from timetable_repository import (
    get_divisions,
    get_timetable_by_division
)

from json_generator import save_division_json


# --------------------------------------------------
# Paths
# --------------------------------------------------

PROJECT_ROOT = os.path.dirname(
    os.path.dirname(
        os.path.dirname(
            os.path.abspath(__file__)
        )
    )
)

UPLOAD_DIR = os.path.join(
    PROJECT_ROOT,
    "storage",
    "uploads"
)

OUTPUT_DIR = os.path.join(
    PROJECT_ROOT,
    "storage",
    "timetable-json"
)


# --------------------------------------------------
# Utility
# --------------------------------------------------

def clean_filename(division_name):

    return division_name.replace(" ", "_") + ".json"


# --------------------------------------------------
# Main Pipeline
# --------------------------------------------------

def main():

    print("\n========================================")
    print("       VIERP TIMETABLE PIPELINE")
    print("========================================")
    uploaded_files = [
            file
            for file in os.listdir(UPLOAD_DIR)
            if file.lower().endswith(".xlsx")
    ]

    if not uploaded_files:
        raise FileNotFoundError(
                "No .xlsx timetable file found in storage/uploads."
            )

    EXCEL_FILE = os.path.join(
            UPLOAD_DIR,
            uploaded_files[0]
        )

    print(
            "Input Excel file:",
            EXCEL_FILE
        )


    # ------------------------------------------
    # 1. READ EXCEL
    # ------------------------------------------

    print("\n[1/5] Reading Excel file...")

    records = read_timetable(EXCEL_FILE)

    print(
        "Excel records read:",
        len(records)
    )


    # ------------------------------------------
    # 2. VALIDATE
    # ------------------------------------------

    print("\n[2/5] Validating Excel data...")

    # Check columns
    validate_columns(records)

    # Check required fields and periods
    record_errors = validate_records(records)

    # Check day and time
    schedule_errors = validate_schedule(records)

    errors = record_errors + schedule_errors

    if errors:

        print("\nValidation failed.")

        for error in errors:
            print(error)

        raise ValueError(
            f"Validation failed with {len(errors)} error(s)."
        )

    print("Excel data is valid.")


    # ------------------------------------------
    # 3. TRANSFORM
    # ------------------------------------------

    print("\n[3/5] Transforming records...")

    transformed_records = transform_records(records)

    print(
        "Records transformed:",
        len(transformed_records)
    )


    # ------------------------------------------
    # 4. MYSQL IMPORT
    # ------------------------------------------

    print("\n[4/5] Importing records into MySQL...")

    import_timetable(
        transformed_records
    )


    # ------------------------------------------
    # 5. GENERATE JSON
    # ------------------------------------------

    print("\n[5/5] Generating JSON files...")

    os.makedirs(
        OUTPUT_DIR,
        exist_ok=True
    )

    divisions = get_divisions()

    print(
        "Total divisions:",
        len(divisions)
    )

    generated = 0

    for division in divisions:

        print(
            f"\nProcessing division: {division}"
        )

        records = get_timetable_by_division(
            division
        )

        print(
            "Records:",
            len(records)
        )

        filename = clean_filename(
            division
        )

        output_file = os.path.join(
            OUTPUT_DIR,
            filename
        )

        save_division_json(
            division,
            records,
            output_file
        )

        generated += 1


    # ------------------------------------------
    # COMPLETE
    # ------------------------------------------

    print("\n========================================")
    print("       PIPELINE COMPLETED")
    print("========================================")

    excel_record_count = len(records)
    records = read_timetable(EXCEL_FILE)
    print(
        "Excel records      :",
        excel_record_count
    )

    print(
        "Records transformed:",
        len(transformed_records)
    )

    print(
        "Divisions processed:",
        generated
    )

    print(
        "JSON output folder :",
        OUTPUT_DIR
    )

    print("========================================\n")


if __name__ == "__main__":
    main()