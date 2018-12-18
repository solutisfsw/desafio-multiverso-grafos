"""Packaging settings."""

import codecs
import os
from subprocess import call


from setuptools import Command, find_packages, setup
from setuptools.command.develop import develop
from setuptools.command.install import install

from src import __VERSION__

PKG_ROOT = os.path.abspath(os.path.dirname(__file__))
with codecs.open(os.path.join(PKG_ROOT, 'README.md'), encoding='utf-8') as file:
    LONG_DESCRIPTION = file.read()

class PostDevelopCommand(develop):
    """Post-installation for development mode."""

    def run(self):
        develop.run(self)
        os.system('echo "Install ran OK"')


class PostInstallCommand(install):
    """Post-installation for installation mode."""

    def run(self):
        os.system('echo "solveit install"')
        install.run(self)


class RunTests(Command):
    """Run all tests."""
    description = 'run tests'
    user_options = []

    def initialize_options(self):
        pass

    def finalize_options(self):
        pass

    def run(self):
        """Run all tests!"""
        errno = call(['python', '-m', 'unittest', 'discover', 'tests/'])
        raise SystemExit(errno)

setup(
    name='solveit',
    version=__VERSION__,
    description='CLI program to execute the solution to the Desafio Multiverso.',
    long_description=LONG_DESCRIPTION,
    url='https://github.com/heitorgo1/desafio-multiverso-grafos',
    author='Heitor Rodrigues',
    author_email='heitor1994.hr@gmail.com',
    license='UNLICENSED',
    classifiers=[
        'Topic :: Utilities',
        'Natural Language :: English',
        'Operating System :: OS Independent',
        'Programming Language :: Python :: 3.7',
    ],
    keywords='cli',
    packages=find_packages(exclude=['tests*']),
    install_requires=[
        'docopt==0.6.2',
    ],
    entry_points={
        'console_scripts': [
            'solveit=src.cli:main',
        ],
    },
    cmdclass={
        'develop': PostDevelopCommand,
        'install': PostInstallCommand,
        'test': RunTests,
    },
)
