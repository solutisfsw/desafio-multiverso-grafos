import argparse

def main():
    parser = argparse.ArgumentParser(description="CLI program to execute the solution to the Desafio Multiverso.")
    group = parser.add_mutually_exclusive_group()
    group.add_argument("--show-solution", action="store_true", help="Print the solution to the requested challenge.")
    args = parser.parse_args()

    if args.show_solution:
        pass

if __name__ == "__main__":
    main()